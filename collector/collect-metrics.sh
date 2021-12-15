#!/bin/sh
#############################################################################
# Script : collect-metrics.sh
#----------------------------------------------------------------------------
# $Author$				Francis Margelidon
# $CreatedDate$				11/12/2021
# $UpdateDate$				N/A
# $Version$                             1.0
#----------------------------------------------------------------------------
# Description : This script is designed to collect system metrics 
#----------------------------------------------------------------------------
# Parameters :  OUTPUT_Location Location to store output file
#----------------------------------------------------------------------------
# Exit code :   0 if ok
#               1 if syntaxe error
#               2 if cannot write output file
#----------------------------------------------------------------------------
#############################################################################

#============================================================================
#              F  U  N  C  T  I  O  N  S       P  A  R  T                   =
#============================================================================
#----------------------------------------------------------------------------
# This function is designed to write metadata in output file                -
# params       : - none                                                     -
# return codes: *                                                           -
#----------------------------------------------------------------------------
function WriteMetadata {
  time=$(date +"%Y-%m-%dT%H:%M:%S%:z")
  node=$(hostname)

  WriteFile "  \"metadata\": {"
  WriteFile "    \"time\":\"${time}\","
  WriteFile "    \"node\":\"${node}\","
  WriteFile "},"
}

#----------------------------------------------------------------------------
# This function is designed to write metrics in output file                 -
# params       : - none                                                     -
# return codes: *                                                           -
#----------------------------------------------------------------------------
function WriteMetrics {
  # Get average machine load for 1min, 5min and 15min
  set -- $(cat /proc/loadavg)
  nodeLoads1=$1
  nodeLoads5=$2
  nodeLoads15=$3

  WriteFile "  \"metrics\": {"
  WriteFile "    \"node_load1\": {\"value\":${nodeLoads1} },"
  
  # Get process name with pid, rss memory and elapsed time in seconds
  WriteFile "    \"process_cpu_seconds\":["
  while read -r process; do
    set -- $(echo ${process})
    pid=$1
    etimes=$2
    shift 2 
    name=$*

    WriteFile "      {\"pid\": ${pid}, \"name\": \"${name}\", \"value\": ${etimes} },"
  done <<< "$(ps -eo pid,etimes,command --no-headers)"
  WriteFile "    ],"

  WriteFile "    \"process_rss_bytes\":["
  while read -r process_list; do
    set -- $(echo ${process_list})
    pid=$1
    rss=$2
    shift 2 
    name=$*

    WriteFile "      {\"pid\": ${pid}, \"name\": \"${name}\", \"value\": ${rss} },"
  done <<< "$(ps -eo pid,rss,command --no-headers)"
    WriteFile "    ]"

  WriteFile "  }"
}

#----------------------------------------------------------------------------
# This function is designed to write text in output file                    -
# params       : - text                                                      -
# return codes: * 0 if ok                                                   -
#               * 2 if cannot write file                                    -
#----------------------------------------------------------------------------
function WriteFile {
  typeset l_text="$1"

  echo "${l_text}" >> ${OUTPUT_LOCATION}/${OUTPUT_FILE}
  if [[ $? -ne 0 ]]; then
    echo "Failed to write output file into ${OUTPUT_LOCATION}"
    exit 2
  fi
}


#============================================================================
#                         M  A  I  N        P  A  R  T                      =
#============================================================================
# --- check the syntax & test the number of parameters ----------------------
status=0
scriptName=$(basename $0)
usage="Usage is ${scriptName} <OUTPUT_Location>"
if [[ $# -lt 1 ]]; then
  echo "${scriptName} : ${usage}"
  exit 1
fi

OUTPUT_LOCATION=$1
OUTPUT_FILE=$(date +"%Y%m%d%H%M%S").json

# Test if output folder exist
if [ ! -d ${OUTPUT_LOCATION} ]; then
  mkdir ${OUTPUT_LOCATION}
  if [[ $? -ne 0 ]]; then
    echo "Failed to create output folder"
    exit 2
  fi
fi

# Start json output file
WriteFile "{"

# Write metadata in output file
WriteMetadata

# Write metrics in output file
WriteMetrics

# End json output file
WriteFile "}"

exit $status

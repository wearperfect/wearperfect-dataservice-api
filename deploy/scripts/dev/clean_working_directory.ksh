#!/bin/ksh
#Clean all content in the working directories.
#CDGC_BMD_STAGE_DIR=/apps/udeploy/dlens_ucd3_etldlecd/var/work/DOE_CDGC_BMD/DEV/zip/stage
#CDGC_BMD_CHANGEME_DIR=/apps/udeploy/dlens_ucd3_etldlecd/var/work/DOE_CDGC_BMD/DEV/zip/changeme

CDGC_BMD_STAGE_DIR=/Users/tejamanne/Desktop/stage
CDGC_BMD_CHANGEME_DIR=/Users/tejamanne/Desktop/changeme

echo "START"
echo "Clean working directories."

#Create STAGE and CHANGEME directories if not present.
mkdir -p $CDGC_BMD_STAGE_DIR
mkdir -p $CDGC_BMD_CHANGEME_DIR

echo "Deleting files and folders from $CDGC_BMD_CHANGEME_DIR."
rm -rvf $CDGC_BMD_LIBS_DIR

echo "Deleting files and folders from $CDGC_BMD_CHANGEME_DIR."
rm -rvf $CDGC_BMD_CHANGEME_DIR

echo "END"


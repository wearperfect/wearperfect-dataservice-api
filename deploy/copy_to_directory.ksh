#!/bin/ksh
#Copy content from extracted content folder to libs folder
#CDGC_BMD_LIBS_DIR=/apps/src/cdgcacd/dev/cdgc_bmd
#CDGC_BMD_CHANGEME_DIR=/apps/udeploy/dlens_ucd3_etldlecd/var/work/DOE_CDGC_BMD/DEV/zip/changeme

CDGC_BMD_LIBS_DIR=/Users/tejamanne/Desktop/libs
CDGC_BMD_CHANGEME_DIR=/Users/tejamanne/Desktop/src

mkdir -p $CDGC_BMD_LIBS_DIR

echo "START"
echo "Copy data from $CDGC_BMD_CHANGEME_DIR to $CDGC_BMD_LIBS_DIR"
cp -Rv $CDGC_BMD_CHANGEME_DIR/* $CDGC_BMD_LIBS_DIR/  
echo "END"

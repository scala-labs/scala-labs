#!/bin/bash
#SOURCE_DIR=labs
SOURCE_DIR=solutions
TARGET_DIR=material
ARTIFACT_NAME="scala-training-${SOURCE_DIR}.zip"

function removeDir {
find $TARGET_DIR -name $1 -type d -exec rm -rf {} \;
                }  

function removeFiles {
find $TARGET_DIR -name $1 -exec rm -rf {} \;
}

#cleanup target
rm -rf $TARGET_DIR
mkdir $TARGET_DIR
rm -f $ARTIFACT_NAME

#basic copy
cp -r $SOURCE_DIR/* $TARGET_DIR

rm $ARTIFACT_NAME

#remove directories
removeDir advanced
removeDir intermediate
removeDir bin
removeDir demo
removeDir logs
removeDir target
removeDir project/project
removeDir worksheets
removeDir misc
removeFiles "*.iml"
removeFiles ".classpath"
removeFiles ".project"

cd $TARGET_DIR
zip -r $ARTIFACT_NAME *
mv $ARTIFACT_NAME ..




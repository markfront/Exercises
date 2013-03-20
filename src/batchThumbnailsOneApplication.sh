#!/bin/bash

# This script will run batchThumbnailsOneProject.sh for each project of the given application.
# Assume the subdirectory for a valid PlanSource project starts with a number, e.g., 11965, or 20059.
# It will be started as a cron task every workday 6pm-6am, and all day every weekend.

# Upon the first run, it creates a file "unfinished-projects-<appName>.txt" which contains
# all projects in that application. It will check the current day and time every 10 files,
# to decide whether it should stop. If it's time to stop, it will write all unfinished
# projects back to the file. Next time it gets re-started, it will look for the
# file and resume the processing. All created thumbnails will be skipped (i.e., not re-created).


# assign default values to the global variables

actionOption="create"                 # "create": create thumbnails (default); "delete": delete thumbnails;
                                      # "average": to compute average file sizes of the application
replaceOld="no"                       # what to do with existing thumbnails ? "yes" (default): to replace, "no" to skip
convertTool="/usr/local/bin/convert"  # the convert util of ImageMagick (default install location)
thumbnailDimension="400x300"               # default dimension for the resulting thumbnails
thumbnailFileName="thumbnail-400x300.gif"  # default file name for the resulting thumbnails

projectDir=""            # the absolute path a project to be processed, e.g., /planroom1/fraser/103
unfinishedProjects=""
projectListFile=""

checkPoint=0            # a flag indicating whether should check current time
canContinue="yes"       # a flag indicating whether can continue processing
isInterrupted="no"      # a flag indicating whether the script is to be stopped before finish

errorsLogFile=""
resultLogFile=""

# the global variables used to count things for each project.
# will be reset to 0 when start processing a new project.

processedFileCount=0    # to count how many files have been processed

directoryCount=0        # to count how many directories and subdirectories are visited
pdfFileCount=0          # to count how many pdf file are processed
imageFileCount=0        # to count how many image files are processed
ignoredFileCount=0      # to count how many files are ignored

goodThumbnailCount=0    # to count how many thumbnails are created OK, i.e., file length > 0
badThumbnailCount=0     # to count how many thumbnails are failed in creation. (i.e., file length == 0)

goodPdfThumbnailCount=0 # to count good thumbnails created for PDF files
badPdfThumbnailCount=0  # to count bad thumbnails created for PDF files

goodImgThumbnailCount=0 # to count good thumbnails created for image files
badImgThumbnailCount=0  # to count bad thumbnails created for image files

removeThumbnailCount=0  # to count how many thumbnails are removed (for "delete" action)

averageThumbnailFileSize=0       # to calculate average file size of all thumbnails
averagePdfThumbnailFileSize=0    # to calculate avearge thumbnail file size for PDFs
averageImgThumbnailFileSize=0    # to calculate average thumbnail file size for images (Tiff, JPG, PNG, GIF, etc)


###############################################
# the function to check current day and time
# to decide if continue to run the script
###############################################

function checkCanContinue()
{
   local currentDay=`date +%u`   # day of the week (1-7), e.g., 3 means Wednesday.
   local currentHour=`date +%H`  # hour of the day (0-23), e.g., 10 means 10am, 22 means 10pm.
   local currentMin=`date +%M`   # minute of the hour (0-59).

   #echo "currentDay=$currentDay, currentHour=$currentHour, currentMin=$currentMin"

   if [ "$currentDay" -eq "6" ] || [ "$currentDay" -eq "7" ]
   then
      canContinue="yes"
   else
      if [ "$currentHour" -ge "18" ] || [ $currentHour -lt "6" ]
      then
         canContinue="yes"
      else
         canContinue="no"
      fi
   fi
}


################################################
# save results for one project to the log file
################################################

function saveResultsToLogFile()
{
   echo "summary of processing $projectDir:" >> $resultLogFile
   echo >> $resultLogFile
   echo "action=$actionOption" >> $resultLogFile
   echo "directories traversed: $directoryCount" >> $resultLogFile
   echo "image files processed: $imageFileCount" >> $resultLogFile
   echo "PDF   files processed: $pdfFileCount"   >> $resultLogFile

   case "$actionOption" in
      "create")

         echo "convertTool   = $convertTool"   >> $resultLogFile
         echo "thumbnailDimension = $thumbnailDimension" >> $resultLogFile
         echo "thumbnailFileName = $thumbnailFileName" >> $resultLogFile
         echo >> $resultLogFile

         echo "total thumbnails created : $goodThumbnailCount" >> $resultLogFile
         echo "thumbnails created for PDF files: $goodPdfThumbnailCount" >> $resultLogFile
         echo "thumbnails created for IMG files: $goodImgThumbnailCount" >> $resultLogFile
         echo >> $resultLogFile

         echo "total thumbnails failed  : $badThumbnailCount"  >> $resultLogFile
         echo "thumbnails failed for PDF files: $badPdfThumbnailCount"  >> $resultLogFile
         echo "thumbnails failed for IMG files: $badImgThumbnailCount"  >> $resultLogFile
         echo >> $resultLogFile

         doAverage

         echo "average thumbnail size: $averageThumbnailFileSize KB" >> $resultLogFile
         echo "average PDF thumbnail size: $averagePdfThumbnailFileSize KB" >> $resultLogFile
         echo "average Img thumbnail size: $averageImgThumbnailFileSize KB" >> $resultLogFile
         echo >> $resultLogFile
         ;;

      "delete")
         echo "thumbnailFileName = $thumbnailFileName" >> $resultLogFile
         echo >> $resultLogFile
         echo "thumbnails removed : $removeThumbnailCount"
         echo "thumbnails removed : $removeThumbnailCount" >> $resultLogFile
         echo >> $resultLogFile
         ;;

      "average")

         echo "thumbnailDimension = $thumbnailDimension" >> $resultLogFile
         echo "thumbnailFileName = $thumbnailFileName" >> $resultLogFile
         echo >> $resultLogFile

         echo "total thumbnails created : $goodThumbnailCount" >> $resultLogFile
         echo "thumbnails created for PDF files: $goodPdfThumbnailCount" >> $resultLogFile
         echo "thumbnails created for IMG files: $goodImgThumbnailCount" >> $resultLogFile
         echo >> $resultLogFile

         echo "total thumbnails failed  : $badThumbnailCount"  >> $resultLogFile
         echo "thumbnails failed for PDF files: $badPdfThumbnailCount"  >> $resultLogFile
         echo "thumbnails failed for IMG files: $badImgThumbnailCount"  >> $resultLogFile
         echo >> $resultLogFile

         doAverage

         echo "average thumbnail size: $averageThumbnailFileSize KB"
         echo "average PDF thumbnail size: $averagePdfThumbnailFileSize KB"
         echo "average Img thumbnail size: $averageImgThumbnailFileSize KB"
         echo

         echo "average thumbnail size: $averageThumbnailFileSize KB" >> $resultLogFile
         echo "average PDF thumbnail size: $averagePdfThumbnailFileSize KB" >> $resultLogFile
         echo "average Img thumbnail size: $averageImgThumbnailFileSize KB" >> $resultLogFile
         echo >> $resultLogFile
         ;;

   esac
}


############################################
# the function to handle a directory
############################################

function processDirectory ()
{
   local currDir=$1
   local lowerCaseName=""

   directoryCount=`expr $directoryCount + 1`

   # scan each file or subdirectories in this directory

   local fileList=`find $currDir -maxdepth 1`

   local file=""

   canContinue="yes"

   for file in $fileList
   do
      #echo "file = $file"
      lowerCaseName=`echo $file | tr A-Z a-z`

      if [ "$file" == "$currDir" ] || [ "$file" == "$currDir/" ]
      then
         #echo "$file == $currDir"
         continue
      fi

      if [ -d "$file" ]
      then
        #echo -e "\t$file is a directory"

         case $lowerCaseName in
         *.tif | *.tiff | *.jpg | *.jpeg | *.png | *.gif | *.bmp )
            # this directory is an image drawing
            #echo -e "\t\tprocessing image file: $file"

            processImageFile $file
            ;;
         * )
            # this directory is a subfolder
            #echo -e "\t\tprocessing directory: $file"

            processDirectory $file
            ;;
         esac
      else

         #echo -e "\t$file is a regular file"

         case $lowerCaseName in
         *.pdf )
            # this file is a PDF file
            #echo -e "\t\tprocessing pdf file: $file"

            processPdfFile $file
            ;;
         * )
            # do nothing
            #echo -e "\t\tignoring file: $file"

            ignoredFileCount=`expr $ignoredFileCount + 1`
            ;;
         esac
      fi

      processedFileCount=`expr $processedFileCount + 1`
      checkPoint=`expr $processedFileCount % 10`

      if [ "$checkPoint" -eq "0" ]
      then
         checkCanContinue
         echo "checkPoint: processedFileCount=$processedFileCount, canContinue=$canContinue"
      fi

      if [ "$canContinue" == "no" ]
      then
         # to test the script, can use this branch
         #if [ "$processedFileCount" -lt "100" ]
         #then
         #   isInterrupted="no"
         #   continue
         #else
         #   isInterrupted="yes"     # signal the script to stop
         #   break
         #fi

         isInterrupted="yes"     # signal the script to stop
         break
      else
         isInterrupted="no"     # the script can continue
         continue
      fi
   done
}


############################################
# the function to process an Image file
############################################

function processImageFile ()
{
   local theDir=$1
   local theFile=""
   local thumbnail=$thumbnailFileName

   imageFileCount=`expr $imageFileCount + 1`

   # to get the image file name from the directory name
   # e.g., ABC.tiff is stored in directory /planrooms/citinc/20059/structural/ABC.tiff/

   local lastIndex=0
   local index=0
   local next=0

   local i=0
   while (( i < ${#theDir} ))
   do
      char=$(expr substr "$theDir" $i 1)
      #echo "<$char>"

      if [ "$char" == "/" ]
      then
         lastIndex=$i
      fi
      i=`expr $i + 1`
   done

   theFile="${theDir:$lastIndex}"

   #echo "theFile = $theFile"

   # to check if the action is to "delete" or to "create"

   handleThumbnail  $theDir"/"$theFile  $theDir"/"$thumbnail  "no"
}


###########################################
# the funciton to process a PDF file
###########################################

function processPdfFile ()
{
   local theFile=$1     # the absolute path to the pdf file
   local thumbnail=""   # the absolute path to the thumbnail file

   # e.g., theFile="/home/bgu/temp/planrooms/citinc/1/bug-JPedal-deadlock/10407A_02c_V1.pdf"
   # then, thumbnail="/home/bgu/temp/planrooms/citinc/1/bug-JPedal-deadlock/10407A_02c_V1.pdf.thumbnail-400x300.jpg"

   thumbnail=$theFile"."$thumbnailFileName

   pdfFileCount=`expr $pdfFileCount + 1`

   # to check if the action is to delete or to create thumbnails

   handleThumbnail  $theFile  $thumbnail "yes"
}


###########################################
# the function to delete or create the thumbnail
# according to the atcionOption
###########################################

function handleThumbnail ()
{
   local theFile=$1    # absolute path to the input  image/pdf file
   local thumbnail=$2  # absolute path to the output thumbnail file
   local isPDF=$3    # "yes" if the file is PDF, otherwise "no"

   # to check if the action is to delete or to create thumbnails, or to get average thumbnail file size

   case "$actionOption" in
   "delete")
      echo "rm -f $thumbnail"
      rm -f $thumbnail
      removeThumbnailCount=`expr $removeThumbnailCount + 1`
      ;;

   "average")
      echo "sum up file size: $thumbnail"
      doSum  $thumbnail  $isPDF
      ;;

   "create")

      # create a new thumbnail if the thumbnail does not exists, or file length is 0,
      # or user wants to delete old ones (i.e., the new thumbnail overwrites the old one)

      if [ ! -e $thumbnail ] || [ ! -s $thumbnail ] || [ "$replaceOld" == "yes" ]
      then
         createThumbnail  $theFile  $thumbnail  $isPDF
      fi
      ;;

   "*")
      ;;
   esac
}


########################################################
# the function that actually generates the thumbnail
########################################################

function createThumbnail ()
{
   local inputFile=$1   # absolute path to the input  image/pdf file
   local outputFile=$2  # absolute path to the output thumbnail file
   local isPDF=$3       # "yes" if it is PDF, otherwise "no"

   # the actual command to create the thumbnail for the first page of the given file

   command="$convertTool -colorspace RGB -thumbnail $thumbnailDimension $inputFile[0] $outputFile"

   echo "$command"

   $command

   # sum up thumbnail file size to compute average

   doSum  $outputFile  $isPDF
}


######################################################
# the function to calculate thumbnail file size
######################################################

function doSum ()
{
   local thumbnailFile=$1   # absolute path to the input  image/pdf file
   local madeForPDF=$2           # "yes" if the thumbnail is created for a PDF file, otherwise "no"

   local thumbnailFileSize=0

   # to compute file size

   if [ -s $thumbnailFile ]  # thumbnail is not 0 size
   then
      goodThumbnailCount=`expr $goodThumbnailCount + 1`
      thumbnailFileSize=`cat $thumbnailFile | wc -c`
      thumbnailFileSize=`expr $thumbnailFileSize / 1024`
      averageThumbnailFileSize=`expr $averageThumbnailFileSize + $thumbnailFileSize`

      echo "thumbnailFileSize = $thumbnailFileSize KB"

      if [ "$madeForPDF" == "yes" ]
      then
         averagePdfThumbnailFileSize=`expr $averagePdfThumbnailFileSize + $thumbnailFileSize`
         goodPdfThumbnailCount=`expr $goodPdfThumbnailCount + 1`
      else
         averageImgThumbnailFileSize=`expr $averageImgThumbnailFileSize + $thumbnailFileSize`
         goodImgThumbnailCount=`expr $goodImgThumbnailCount + 1`
      fi
   else
      echo "thumbnailFileSize = 0 KB"

      badThumbnailCount=`expr $badThumbnailCount + 1`
      # save the absolute path of this error file to the error log file
      echo "$inputFile" >> $errorsLogFile

      if [ "$madeForPDF" == "yes" ]
      then
         badPdfThumbnailCount=`expr $badPdfThumbnailCount + 1`
      else
         badImgThumbnailCount=`expr $badImgThumbnailCount + 1`
      fi
   fi
}


################################################
# the function to calculate average file size
################################################

function doAverage ()
{
   # to avoid "divided by zero"
   if [ $goodThumbnailCount -eq 0 ]
   then
      goodThumbnailCount=`expr $goodThumbnailCount + 1`
   fi

   averageThumbnailFileSize=`expr $averageThumbnailFileSize / $goodThumbnailCount`

   # to avoid "divided by zero"
   if [ $goodPdfThumbnailCount -eq 0 ]
   then
      goodPdfThumbnailCount=`expr $goodPdfThumbnailCount + 1`
   fi

   averagePdfThumbnailFileSize=`expr $averagePdfThumbnailFileSize / $goodPdfThumbnailCount`

   # to avoid "divided by zero"
   if [ $goodImgThumbnailCount -eq 0 ]
   then
      goodImgThumbnailCount=`expr $goodImgThumbnailCount + 1`
   fi

   averageImgThumbnailFileSize=`expr $averageImgThumbnailFileSize / $goodImgThumbnailCount`
}


##############################################
# the function to process one project
##############################################

function processOneProject()
{
   echo "begin processing project $project ......"

   timeStarted=`date +%Y-%m-%d-%H:%M:%S`

   processDirectory $projectDir

   echo "=========================" >> $resultLogFile

   saveResultsToLogFile

   if [ $isInterrupted == "no" ]
   then
      echo "processing completed" >> $resultLogFile
   else
      echo "processing interrupted" >> $resultLogFile
   fi

   timeStopped=`date +%Y-%m-%d-%H:%M:%S`

   echo "" >> $resultLogFile
   echo "time started: $timeStarted"  >> $resultLogFile
   echo "time stopped: $timeStopped"  >> $resultLogFile

   echo "=========================" >> $resultLogFile
}


###################################################
# the main script goes here
###################################################


appRootDir=$1          # the root dir for the application, e.g., /planroom1/fraser
appName=$2             # the abreviation name for the application, e.g., fraser
logFileDir=$3          # where to store the log file of this program, e.g., /planrooms/thumbnaillogs

# remove trailing slash of the directory path, e.g., "/planroom1/bunting/" --> "/planroom1/bunting"

appRootDir=`echo "$appRootDir" | sed -e 's,/$,,g'`
logFileDir=`echo "$logFileDir" | sed -e 's,/$,,g'`

# set absolute path to the log files

time1=`date +%Y-%m-%d-%H:%M:%S`

today=`date +%Y-%m-%d`
errorsLogFile=$logFileDir"/batchThumbnails-errors-"$appName"-"$today".log"    # e.g., /home/bgu/errorThumbnails-bcca-2010-03-04.log
resultLogFile=$logFileDir"/batchThumbnails-result-"$appName"-"$today".log"    # e.g., /home/bgu/errorThumbnails-bcca-2010-03-04.log

# output the arguments to screen

echo
echo "appRootDir    = $appRootDir"
echo "appName       = $appName"
echo "logFileDir    = $logFileDir"

#echo "actionOption  = $actionOption"
#echo "replaceOld    = $replaceOld"
#echo "convertTool   = $convertTool"
#echo "thumbnailDimension = $thumbnailDimension"
#echo "thumbnailFileName  = $thumbnailFileName"

echo
echo "resultLogFile = $resultLogFile"
echo "errorsLogFile = $errorsLogFile"
echo

projectListFile="unfinished-projects-"$appName".txt"

# get the list of unfinished projects

if [ ! -e $projectListFile ]     # the first time run the application
then
   # all the project directories start with a number

   unfinishedProjects=`ls $appRootDir | grep "^[0-9].*"`

   # write them to the projectListFile

   for project in $unfinishedProjects
   do
      echo $project >> $projectListFile
   done
else
   # read unfinished projects from the file

   unfinishedProjects=`cat $projectListFile`
fi

# start processing the projects

projectDir=""     # the absolute path a project to be processed, e.g., /planroom1/fraser/103
cursor1=0         # used to remember the last project processed

for project in $unfinishedProjects
do
   cursor1=`expr $cursor1 + 1`

   projectDir=$appRootDir"/"$project

   processOneProject

   if [ "$isInterrupted" == "no" ]
   then
      continue
   else
      break
   fi
done

# output unfinished projects to the projectListFile

echo "" > $projectListFile   # clear the content of the file

if [ "$isInterrupted" == "no" ]
then
   echo "Processing succeeded. All projects in $appName are processed."
else
   cursor2=0        # used to loop through the projects

   for project in $unfinishedProjects
   do
      cursor2=`expr $cursor2 + 1`

      if [ "$cursor2" -ge "$cursor1" ]
      then
         echo "$project" >> $projectListFile
      fi
   done

   echo "Processing interrupted. Unfinished projects in $appName are saved."
fi

time2=`date +%Y-%m-%d-%H:%M:%S`

echo
echo "script started at $time1"
echo "script stopped at $time2"
echo


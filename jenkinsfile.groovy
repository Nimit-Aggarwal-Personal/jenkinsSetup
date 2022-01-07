import groovy.io.*
import java.nio.file.Paths

def resultfolder = "C:\\Users\\naggarwal\\Documents\\GitHub\\securego_internal\\Results\\"
def currentResultFolder
def resultFileName = "VRBankingResultFile.html"
  pipeline {
    agent any
    stages {
      stage('Starting test cases') {
        steps {
          bat """
			cd C:\\Users\\naggarwal\\Documents\\GitHub\\securego_internal
			run.bat 
			"""
          echo "Starting test cases.........."
        }
      }
      stage('Generating result') {
        steps {
          script {
			def outputp = powershell(returnStdout: true, script: 'gci "C:\\Users\\naggarwal\\Documents\\GitHub\\securego_internal\\Results" | sort -Property LastWriteTime -Descending | select -First 1').toString()
			outputp = outputp.substring(outputp.indexOf("Result_")).trim()
			println("${outputp}")
			currentResultFolder =  new File(resultfolder, outputp).toString()
			currentResultFolder =  new File(currentResultFolder, resultFileName).toString()
			println("${currentResultFolder}")
            echo "Generating result.........."
			def zipFileName = "${outputp}" + ".zip"
			powershell(returnStatus: true, script: "Compress-Archive ${new File(resultfolder, outputp).toString()} ${new File(env.WORKSPACE, zipFileName).toString()}")
			def copyCommand = "xcopy ${new File(resultfolder, outputp).toString()} ${new File(env.WORKSPACE, zipFileName).toString()} /E /H /C /I"
			println(copyCommand)
			bat "${copyCommand}"
			archiveArtifacts artifacts: '/*'"${zipFileName}"

          }
        }
      }
    }
  }
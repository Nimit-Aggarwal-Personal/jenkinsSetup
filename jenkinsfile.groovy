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
			println("${outputp}")
			echo "Printing powershell output.........."
			outputp = outputp.substring(outputp.indexOf("Result_"))
			println("${outputp}")
			currentResultFolder =  resultfolder + outputp
			currentResultFolder =  new File(currentResultFolder, resultFileName)
			println("${currentResultFolder}")
            echo "Generating result.........."
			archiveArtifacts artifacts: currentResultFolder

          }
        }
      }
    }
  }
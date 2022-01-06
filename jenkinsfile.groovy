import groovy.io.*

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
            def outputp = powershell(returnStatus: true, script: 'gci "C:\\Users\\naggarwal\\Documents\\GitHub\\securego_internal\\Results" | sort -Property LastWriteTime -Descending | select -First 1').toString()
			echo "Printing powershell output.........."
			println "${outputp}"
			outputp = outputp.substring(outputp.indexOf("Result_"), 5)
			echo "Printing substring output.........."
			println "${outputp}"
            echo "Generating result.........."
          }
        }
      }
    }
  }
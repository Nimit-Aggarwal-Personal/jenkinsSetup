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
            dh = new File("C:\\Users\\naggarwal\\Documents\\GitHub\\securego_internal\\Results")
            dh.eachFile {
              println(it)
            }

            echo "Generating result.........."
          }
        }
      }
    }
  }
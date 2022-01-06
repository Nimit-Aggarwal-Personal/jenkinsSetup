import groovy.io.*

pipeline{
agent any
stages 
{
stage('Starting test cases') 
{
steps{
bat """
    cd C:\\Users\\naggarwal\\Documents\\GitHub\\securego_internal
    run.bat
  """
echo "Starting test cases.........."
}
}
stage('Generating result') 
{
steps{
	script {
		def testEnvironment = "Prod"
		println "${testEnvironment}"
		def listfiles(dir) {
		dlist = []
		flist = []
		new File(dir).eachDir {dlist << it.name }
		dlist.sort()
			new File(dir).eachFile(FileType.FILES, {flist << it.name })
		flist.sort()
		return (dlist << flist).flatten()
		}

		fs = listfiles(".")
		fs.each {
		println it
	}
		echo "Generating result.........."
		}
}
}
}
}

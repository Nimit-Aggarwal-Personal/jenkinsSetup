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
		def fetFilenamesFromDir(def dir, def list){
dir.eachFileRecurse (FileType.FILES) { file ->
file = file.toString()
if (file.endsWith("json")){
list << file
}
}
}

node('master'){
def list = []

def dir = new File("dirPathToBeDefined")
fetFilenamesFromDir(dir,list)

for (i in list){
print i
print("\n")
}
		echo "Generating result.........."
		}
}
}
}
}

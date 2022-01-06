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
		def list = []
		def dir = new File("C:\\Users\\naggarwal\\Documents\\GitHub\\securego_internal\\Results")
		println "${dir}"
dir.eachFileRecurse (FileType.FILES) { file ->
file = file.toString()
if (file.endsWith("json")){
list << file
}
}



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

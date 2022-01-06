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
		println ${testEnvironment}
		echo "Generating result.........."
		}
}
}
}
}

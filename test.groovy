pipeline{
   agent any
  stages{
   stage("test"){
    steps{


    sh''' #!/bin/bash
	echo ${params.test_choice}
'''

}



}


}



}

pipeline{
   agent any
  stages{
   stage("test"){
    steps{


    sh''' #!/bin/bash
	echo ${test_choice}
'''

}



}


}



}

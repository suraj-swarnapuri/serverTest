pipeline{
   agent any
   parameters{
     string(name:'VAR', defaultValue:'',description:'test var')


}
  stages{
   stage("test"){
    steps{


    sh''' #!/bin/bash
	echo ${params.VAR}
'''

}



}


}



}

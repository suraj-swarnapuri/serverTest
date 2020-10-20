pipeline{
   agent any
  

stages{
        stage("Validate Parameters"){
            steps{
                sh ''' #!/bin/bash
                    echo "validate"
                '''
            }
        }
        stage("Check ServiceNow"){
            steps{
                sh ''' #!/bin/bash
                    echo "check service now"
                '''
            }
        }
        stage("Image sign check"){
            steps{
                sh ''' #!/bin/bash
                    echo "image sign check"
                '''
            }
        }
        stage("Deploy"){
            steps{
             
                    sh''' #!/bin/bash
                     echo ${test_choice}
                    '''
               
            }
        }
        stage("Update ServiceNow"){
            steps{
                sh ''' #!/bin/bash
                    echo "update service now"
                '''
            }
        }
    }



}

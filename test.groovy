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
               job_var = ${VAR}
               build job: 'test_job', parameters: [string(name: 'VAR', value: job_var)]
                input 'wait for approval'
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
             
                    sh'''  #!/bin/bash
            set -ex
            export RUN_ENV=prod
            export RUN_SITE=${RUN_SITE}
            export SOURCE_BRANCH=uat
            export TARGET_BRANCH=prod
            export USERNAME=${USERNAME}
            if [ "${CONTROLLER_POD}" = "POD2" ] ; then
                export HOSTNAME=169.59.198.195
            else
                export HOSTNAME=169.47.70.153
            fi
            export DESCRIBE=${TARGET_HOST}
            if [ "${PLAYBOOK}" = "other" ] ; then
                export PLAYBOOK=${PLAYBOOK_OTHER}
            else
                export PLAYBOOK=${PLAYBOOK}
            fi
            set +x
            CICD_VAULT_SECRET_KEY=${CICD_VAULT_SECRET_KEY}
            set -x
            echo "deploy"
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

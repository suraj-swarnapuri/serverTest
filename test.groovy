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
                build job: 'jjb-open-cr-issue', 
                parameters: [
                    string(name: 'PR_Title', value: String.valueOf(PR_Title)), 
                    string(name: 'PR_Org_Repo', value: String.valueOf(PR_Org_Repo)), 
                    string(name: 'CR_Title', value: String.valueOf(CR_Title)), 
                    string(name: 'CR_Org_Repo', value: String.valueOf(CR_Org_Repo)), 
                    string(name: 'CR_Approvers', value: String.valueOf(CR_Approvers)), 
                    string(name: 'CR_Assignees', value: String.valueOf(CR_Assignees)), 
                    string(name: 'CR_Notify', value: String.valueOf(CR_Notify))
                    ]
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
             
                    sh'''
                            #!/bin/bash
                            set -ex
                            export RUN_ENV=${RUN_ENV} 
                            export RUN_SITE=${RUN_SITE} 
                            export SOURCE_BRANCH=${SOURCE_BRANCH} 
                            export TARGET_BRANCH=${TARGET_BRANCH} 
                            export USERNAME=${USERNAME} 
                            export HOSTNAME=${HOSTNAME} 
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

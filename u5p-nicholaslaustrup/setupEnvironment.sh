# Step One- Fill out the UNIT_FIVE_REPO_NAME and GITHUB_USERNAME

# Step Two - configure your shell to always have these variables.
# For OSX / Linux
# Copy and paste ALL of the properties below into your .bash_profile in your home directly

# For Windows
# Copy and paste ALL of the properties below into your .bashrc file in your home directory



# Fill out the following values

# The path of your repo on github.
#replace YourActualGithubUsername with your Github username matching the case exactly
#This must match the casing of your repo name in github EXACTLY.
#If the casing is different you will have issues.
#for example if your username was tHisIS-bOb this would read:
#export UNIT_TWO_REPO_NAME=u5p-tHisIS-bOb
export UNIT_FIVE_REPO_NAME=u5p-nicholaslaustrup

#Replace yourusernameinlowercase with your github username all lowercase
#This MUST be ALL lowercase or you will have problems
#for example if your username was tHisIS-bOb this would read:
#export GITHUB_USERNAME=thisis-bob
export GITHUB_USERNAME=nicholaslaustrup



# Do not modify the rest of these unless you have been instructed to do so.
export UNIT_FIVE_PROJECT_NAME=unitproject5
export UNIT_FIVE_PIPELINE_STACK=$UNIT_FIVE_PROJECT_NAME-$GITHUB_USERNAME
export UNIT_FIVE_ARTIFACT_BUCKET=$UNIT_FIVE_PROJECT_NAME-$GITHUB_USERNAME-artifacts
export UNIT_FIVE_APPLICATION_STACK=$UNIT_FIVE_PROJECT_NAME-$GITHUB_USERNAME-application
export UNIT_FIVE_SERVICE_STACK=$UNIT_FIVE_PROJECT_NAME-$GITHUB_USERNAME-service
export UNIT_FIVE_SERVICE_STACK_DEV=$UNIT_FIVE_PROJECT_NAME-$GITHUB_USERNAME-service-dev

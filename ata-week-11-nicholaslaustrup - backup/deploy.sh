echo "Deploying Week 11 Group Work Resources - DynamoDB"
aws cloudformation create-stack --stack-name Week11-GroupWork --template-body file://GroupWork/DiscussionCLI/group_work_tables.yaml --capabilities CAPABILITY_NAMED_IAM
echo "Waiting for it to finish deploying.  This may take 2-3 minutes...  But if takes more than 5 minutes then it may have failed. Check your CloudFormation Stack on the AWS UI for errors."
aws cloudformation wait stack-create-complete --stack-name Week11-GroupWork
echo "Populating data in DynamoDB."
aws dynamodb batch-write-item --request-items file://GroupWork/DiscussionCLI/members_seeddata.json
aws dynamodb batch-write-item --request-items file://GroupWork/DiscussionCLI/messages_seeddata.json
aws dynamodb batch-write-item --request-items file://GroupWork/DiscussionCLI/topics_seeddata.json
echo "Done!"
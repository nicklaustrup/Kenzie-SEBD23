echo "Deleting Resources for Week 11"
echo "This may take 2-3 minutes...  But if takes more than 5 minutes then it may have failed. Check your CloudFormation Stack on the AWS UI for errors."
aws cloudformation delete-stack --stack-name Week11-GroupWork
aws cloudformation wait stack-delete-complete --stack-name Week11-GroupWork
echo "Done!"

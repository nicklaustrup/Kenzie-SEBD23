# TEMPLATE Integration Test Plan

## Instructions

## Purpose

This captures the test plan for the testing of endpoints through automated integration tests.

## Product Background

Create user accounts to track comments and check for spam.

### Use Cases to be tested

A new user signs up and immediately posts 1 comment.
A new user signs up. After the probation period, the user posts 20 comments.

# Automated Integration Test Plan

*Organize your tests by use cases from assignment description. Provide the
entire "Use Case:..." section below for each Use Case you will implement
in the project. If you have more than one test case for a given use
case, repeat the "Test Case" section below for each test case in that
use case.*

*The goal should be that any member of your team could take this list of
integration tests and add these automated tests to the integration test
package.*

## Use Case: A new user signs up and immediately posts 1 comment.

### **Test case name: *newUser_postsComment_successful***

**Acceptance criteria:**

1. If a new user posts a single comment within the probationary period of their account, the post is successful.

**Endpoint(s) tested:**

1. '/comment'

**GIVEN (Preconditions):**

1. A new user within probation period

**WHEN (Action(s)):**

1. Creates a comment using the '/comment' endpoint

**THEN (Verification steps):**

1. Post is successful
2. User is not deactivated

**Is there any clean-up needed for this test?**

1. Delete comments from database


## Use Case: A new user signs up. After the probation period, the user posts 20 comments.

### **Test case name: *newUser_posts20CommentAfterProbation_successful***

**Acceptance criteria:**

1. If user posts 20 comments after the probationary period ends, the posts are successful.

**Endpoint(s) tested:**

1. '/comment'

**GIVEN (Preconditions):**

1. A user account after the probation period

**WHEN (Action(s)):**

1. Creates 20 comments using the '/comment' endpoint

**THEN (Verification steps):**

1. Posts are successful
2. User is not deactivated

**Is there any clean-up needed for this test?**

1. Delete comments from database
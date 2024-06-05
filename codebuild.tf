
resource "aws_iam_role" "codebuild_role" {
  name = "codebuild-service-role-for-user"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "codebuild.amazonaws.com"
      }
    }]
  })
}

resource "aws_iam_role_policy" "codebuild_policy" {
  name = "codebuild-policy"
  role = aws_iam_role.codebuild_role.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect   = "Allow"
        Action   = [
          "logs:CreateLogGroup",
          "logs:CreateLogStream",
          "logs:PutLogEvents"
        ]
        Resource = "*"
      },
      {
        Effect   = "Allow"
        Action   = [
          "codecommit:GitPull"
        ]
        # Resource = data.aws_codecommit_repository.revhire_user_repo.arn
        Resource = resource.aws_codecommit_repository.my_repo.arn
      },
      {
        Effect   = "Allow"
        Action   = [
          "secretsmanager:GetSecretValue"
        ]
        Resource = [
          "arn:aws:secretsmanager:us-east-1:914921102753:secret:revhire-job-access-keys-terraform-LL9b5H"
        ]
      },
      {
        Effect   = "Allow"
        Action   = [
          "ecr-public:GetAuthorizationToken"
        ]
        Resource = "*"
      },
      {
        Effect   = "Allow"
        Action   = [
          "eks:DescribeCluster",
          "eks:GetToken"
        ]
        Resource = "arn:aws:eks:us-east-1:914921102753:cluster/my-cluster"
      }
    ]
  })
}



resource "aws_codebuild_project" "revhire-user-build" {
  name          = "revhire-user-build"
  description   = "Build project for revhire-user application"
  service_role  = aws_iam_role.codebuild_role.arn

  artifacts {
    type = "NO_ARTIFACTS"
  }

  environment {
    compute_type                = "BUILD_GENERAL1_SMALL"
    image                       = "aws/codebuild/standard:4.0"
    type                        = "LINUX_CONTAINER"

    environment_variable {
      name  = "REPOSITORY_URI"
      value = "public.ecr.aws/s8l6c1d5/team2-user"
    }
    environment_variable {
      name  = "EKS_CLUSTERNAME"
      value = "my-cluster"
    }
    environment_variable {
      name  = "AWS_ACCESS_KEY_ID" // Update to match your actual variable name
      value = "your key"
    }
    environment_variable {
      name  = "AWS_SECRET_ACCESS_KEY" // Update to match your actual variable name
      value = "your secret key"
    }

    environment_variable {
      name  = "TAG" // Update to match your actual variable name
      value = "latest"
    }
  }

  source {
    type            = "CODECOMMIT"
    location        = resource.aws_codecommit_repository.my_repo.clone_url_http
    buildspec       = "buildspec.yaml"
    git_clone_depth = 1

    git_submodules_config {
      fetch_submodules = true
    }
  }

  logs_config {
    cloudwatch_logs {
      group_name  = "/aws/codebuild/revhire-user-build"
      stream_name = "build-log"
    }
  }
}
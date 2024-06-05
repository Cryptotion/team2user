resource "aws_codecommit_repository" "my_repo" {
  repository_name = "user-service-krishnateam2"
}

resource "null_resource" "clone_repo" {
  provisioner "local-exec" {
    command = <<-EOT
      mkdir gitreponew
      pwd
      git clone ${aws_codecommit_repository.my_repo.clone_url_http} gitreponew/
      cp -r revhire-user-service/* gitreponew/
      cd gitreponew
      git add .
      git commit -m "Initial commit"
      git push -u origin master
    EOT
    interpreter = ["C:\\Program Files\\Git\\bin\\bash.exe", "-c"]
  }

  depends_on = [aws_codecommit_repository.my_repo]
  triggers = {
    always_run = timestamp()
  }
}
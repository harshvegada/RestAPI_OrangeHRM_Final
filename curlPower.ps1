$auth = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTIxNjE3MjMsIm5iZiI6MTY1MjE2MTcyMywianRpIjoiMDk3ODMzMzgtNTA4My00ZWZjLTlkYTAtMTlhNzEwZTA1ZjQ2IiwiZXhwIjoxNjUyMTYyNjIzLCJpZGVudGl0eSI6Im15X3VzZXJuYW1lIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIiwiY3NyZiI6Ijk3NTVlMTE5LTY0MGMtNGJmMi1hZjQ3LWJhNzhjNjk1ZDUxZCJ9.ZECq3vv9R7iUXYYfUh4DZSqDVi28www6uHGOzLXNngs"
#
#$resultResponse = Invoke-WebRequest -Uri "http://localhost:5050/allure-docker-service/projects" -Method GET -Headers @{"Authorization"="Bearer $auth"; "Accept"="application/json"} -ContentType "application/json" -UseBasicParsing | ConvertFrom-Json
#Write-Output "Res: "$resultResponse

$resReq = Invoke-WebRequest -Uri "http://localhost:5050/allure-docker-service/projects" -Method GET -Headers @{"Authorization"="Bearer $auth"} -WebSession $Session
Write-Output "output: " $resReq
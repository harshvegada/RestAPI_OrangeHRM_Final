$ReportPath = "allure-results"
$BaseUrl = "https://allure.connectedproduction.net//allure-api"
$ProjectId = "default"

$params = @{
    "username" = "my_username";
    "password" = "my_password";
}
$request_json = ConvertTo-Json -InputObject $params
$endPoint = "$BaseUrl/allure-docker-service/login"
$resultResponse = Invoke-WebRequest -Uri $endPoint -ContentType 'application/json' -Method Post -Body $request_json -UseBasicParsing | ConvertFrom-Json
$bearer_token = $resultResponse.data[0]
#
#$headers = @{ Authorization = "Bearer $bearer_token" }
#Write-Output "request_json: " $request_json
#Invoke-WebRequest $BaseUrl/allure-docker-service/login -Body $request_json -Method Post



$Request = @{
    "results" = @()
}

$Files = (Get-ChildItem -File -Path "$PSScriptRoot\$ReportPath")
foreach ($Item in $Files)
{
    $Content = Get-Content -Path $Item.FullName
    if ($Content)
    {
        $Result = @{
            "file_name" = $Item.name
            "content_base64" = [Convert]::ToBase64String([IO.File]::ReadAllBytes($Item.FullName))
        }
        $Request.results += $Result;
    }
}

$json = ConvertTo-Json -InputObject $Request

Write-Output "------------------SEND-RESULTS------------------"


$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession

$cookie = New-Object System.Net.Cookie

$cookie.Name = "csrf_refresh_token"
$cookie.Value = "518508ae-2fca-4428-b254-dc29317c23d3"
$cookie.Domain = "allure.connectedproduction.net"

$SendResultsEndpoint = "$BaseUrl/send-results?project_id=$ProjectId"
$result = (Invoke-WebRequest -Uri $SendResultsEndpoint -WebSession $session -Headers $headers -ContentType "application/json" -Method Post -Body $json -UseBasicParsing)


#Cookies's Call


#
#$session.Cookies.Add($cookie);

#$result = (Invoke-WebRequest -Uri $SendResultsEndpoint -WebSession $session -Headers $headers -ContentType "application/json" -Method POST -Body $json -UseBasicParsing )

##If you want to generate reports on demand use the endpoint `GET /generate-report` and disable the Automatic Execution >> `CHECK_RESULTS_EVERY_SECONDS: NONE`
#Write-Output "------------------GENERATE-REPORT------------------"
#$GenerateReportEndpoint = "$BaseUrl/generate-report?project_id=$ProjectId"
#$response = Invoke-RestMethod -Uri "$GenerateReportEndpoint"
#$reportUrl = $response.data.report_url
#$testreport = @"
#_________________________________________________________________________________________________
# _____ _ _
#|_ _| | | | |
# | | ___ ___| |_ _ __ ___ _ __ ___ _ __| |_
# | |/ _ \/ __| __| | '__/ _ \ '_ \ / _ \| '__| __|
# | | __/\__ \ |_ | | | __/ |_) | (_) | | | |_
# \_/\___||___/\__| |_| \___| .__/ \___/|_| \__|
# | |
# |_|
#_________________________________________________________________________________________________
#$response
#_________________________________________________________________________________________________
#"@
#Write-Output $testreport
#Write-Output $reportUrl
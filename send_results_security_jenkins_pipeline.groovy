// Required Jenkins plugins:
// https://plugins.jenkins.io/http_request/
// https://plugins.jenkins.io/pipeline-utility-steps/

// Documentation:
// https://jenkins.io/doc/pipeline/steps/pipeline-utility-steps/
// https://jenkins.io/doc/pipeline/steps/workflow-basic-steps/

import groovy.json.JsonOutput;

class Result {
    String file_name;
    String content_base64
}

allure_server_url = 'http://localhost:5050'
project_id = 'default'
security_user = 'my_username'
security_password = 'my_password'

pattern_allure_results_directory = 'allure-results/*'

static String build_allure_results_json(pattern) {
    def results = []
    def files = findFiles(glob: pattern)
    files.each {
        def b64_content = readFile file: "${it.path}", encoding: 'Base64'
        if (!b64_content.trim().isEmpty()) {
            results.add(new Result(file_name: "${it.name}", content_base64: b64_content))
        } else {
            print("Empty File skipped: ${it.path}")
        }
    }
    print(results)
    JsonOutput.toJson(results: results)
}

static Object get_cookies(response) {
    def cookies_map = [:]
    def cookies = response.headers.get("Set-Cookie")
    cookies.each {
        def cookie = it.substring(0, it.indexOf(';'))
        def cookie_key = cookie.substring(0, cookie.indexOf('='))
        cookies_map[cookie_key] = it
    }
    cookies_map
}

static Object get_cookie_value(cookie) {
    def simple_cookie = cookie.substring(0, cookie.indexOf(';'))
    return simple_cookie.substring(simple_cookie.indexOf('=') + 1, simple_cookie.length())
}

static Object login_to_allure_docker_service(allure_server_url, username, password) {
    def json_credential = JsonOutput.toJson(username: username, password: password)
    httpRequest url: "${allure_server_url}/allure-docker-service/login",
            httpMode: 'POST',
            contentType: 'APPLICATION_JSON',
            requestBody: json_credential,
            consoleLogResponseBody: true,
            validResponseCodes: '200'
}

static Object send_results_to_allure_docker_service(allure_server_url, project_id, results_json) {
    httpRequest url: "${allure_server_url}/allure-docker-service/send-results?project_id=${project_id}",
            httpMode: 'POST',
            contentType: 'APPLICATION_JSON',
            requestBody: results_json,
            consoleLogResponseBody: true,
            validResponseCodes: '200'
}


static Object send_results_to_allure_docker_service_withoutlogin(allure_server_url, project_id, results_json) {
    httpRequest url: "${allure_server_url}/allure-docker-service/send-results?project_id=${project_id}",
            httpMode: 'POST',
            contentType: 'APPLICATION_JSON',
            requestBody: results_json,
            consoleLogResponseBody: true,
            validResponseCodes: '200'
}

static Object generate_allure_report(allure_server_url, project_id) {
    httpRequest url: "${allure_server_url}/allure-docker-service/generate-report?project_id=${project_id}",
            httpMode: 'GET',
            contentType: 'APPLICATION_JSON',
            consoleLogResponseBody: true,
            validResponseCodes: '200'
}

static void main(String[] args) {
    def response = login_to_allure_docker_service(allure_server_url, security_user, security_password)
    def cookies = get_cookies(response)
    print "cookies: ${cookies}"
    def csrf_access_token = get_cookie_value(cookies['csrf_access_token'])
    print "csrf_access_token: ${csrf_access_token}"

    def results_json = build_allure_results_json(pattern_allure_results_directory)
    print(allure_server_url)
    print(project_id)
    print(results_json)
    send_results_to_allure_docker_service(allure_server_url, project_id, results_json)
    generate_allure_report(allure_server_url, project_id)
}

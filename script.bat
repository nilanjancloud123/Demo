curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMDYwZTkwOS01OWRlLTM1NmEtOTJjOS1hYTIxZTg0NTM0NzkiLCJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczpcL1wvYWRtcmxkZW1vLmF0bGFzc2lhbi5uZXQiLCJ1c2VyIjp7ImFjY291bnRJZCI6IjVmMzEzZjgzZTExNTQyMDA0NjJkMjkwZiJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJleHAiOjE2NTI4ODIxMjAsImlhdCI6MTYyMTM0NjEyMH0.-2t8fRDKlm8BZDkSJ9TTwn7QZXKGx6qiRWnwdCnxJuk" -F "file=@test-output\junitreports\TEST-com.mydemo.SauceTestUpdated.xml;type=application/xml" https://api.adaptavist.io/tm4j/v2/automations/executions/junit?projectKey=AD
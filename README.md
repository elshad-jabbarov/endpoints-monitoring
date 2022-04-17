

## API Reference for Users

#### Get all users

```http
  GET /api/users
```

```json
"users": [
            {
                "name": "user",
                "email": "user@gmail.com",
                "accessToken": "4158f7c9-7356-4224-b0d4-1d1ca7b235be",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/users/1"
                    },
                    "user": {
                        "href": "http://localhost:8080/api/users/1"
                    }
                }
            },
            {
                "name": "jon",
                "email": "jon@gmail.com",
                "accessToken": "5ea4f5f2-bb3d-4583-9351-682ec400ad77",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/users/2"
                    },
                    "user": {
                        "href": "http://localhost:8080/api/users/2"
                    }
                }
            },
            {
                "name": "tom",
                "email": "tom@gmail.com",
                "accessToken": "8a47186b-2418-4b36-aa35-43a689e10d8f",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/users/3"
                    },
                    "user": {
                        "href": "http://localhost:8080/api/users/3"
                    }
                }
            },
            {
                "name": "tom2",
                "email": "tom2@gmail.com",
                "accessToken": "5dfbe76b-c5e6-4376-bed1-3f399f06ef21",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/users/4"
                    },
                    "user": {
                        "href": "http://localhost:8080/api/users/4"
                    }
                }
            }
        ]
```

#### Get user

```http
  GET api/users/${id}
```

```json
{
    "name": "user",
    "email": "user@gmail.com",
    "accessToken": "4158f7c9-7356-4224-b0d4-1d1ca7b235be",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/users/1"
        },
        "user": {
            "href": "http://localhost:8080/api/users/1"
        }
    }
}
```

#### Create user

```http
  POST api/users/
```

```json
{
    "name": "user",
    "email": "user@gmail.com"
}
```

#### Update user

```http
  PUT api/users/${id}
```

```json
{
    "name": "user",
    "email": "user@gmail.com"
}
```

#### Delete user

```http
  DELETE api/users/${id}
```


## API Reference for Endoints


#### Get All endpoints by user

```http
  GET /api/endpoints/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `accessToken`      | `Header` | **Required** |


```json
[
    {
        "name": "baeldung",
        "id": 5,
        "monitoringResults": [
            {
                "payload": "{\n  \"success\": true,\n  \"info\": \"To customize this response, check out our docs at https://pipedream.com/docs/workflows/steps/triggers/#customizing-the-http-response\"\n}\n",
                "statusCode": 200,
                "dateOfCheck": "2022-04-17T18:21:24.537+00:00"
            }
        ],
        "createdDate": "2022-04-17T18:12:04.097+00:00",
        "lastCheck": "2022-04-17T18:25:14.580+00:00",
        "url": "https://eoe8vj6kvm12tsx.m.pipedream.net"
    }
]
```

#### Get  endpoints by id

```http
  GET /api/endpoints/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `accessToken`      | `Header` | **Required** |


```json
[
    {
        "name": "baeldung",
        "id": 5,
        "monitoringResults": [
            {
                "payload": "{\n  \"success\": true,\n  \"info\": \"To customize this response, check out our docs at https://pipedream.com/docs/workflows/steps/triggers/#customizing-the-http-response\"\n}\n",
                "statusCode": 200,
                "dateOfCheck": "2022-04-17T18:21:24.537+00:00"
            }
        ],
        "createdDate": "2022-04-17T18:12:04.097+00:00",
        "lastCheck": "2022-04-17T18:25:14.580+00:00",
        "url": "https://eoe8vj6kvm12tsx.m.pipedream.net"
    }
]
```

#### Create  endpoints by id

```http
  POST /api/endpoints/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `accessToken`      | `Header` | **Required** |


```json
[
    {
        "name": "baeldung",
        "url": "https://eoe8vj6kvm12tsx.m.pipedream.net"
    }
]
```

#### Create  endpoints by id

```http
  PUT /api/endpoints/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `accessToken`      | `Header` | **Required** |


```json
[
    {
        "name": "baeldung",
        "url": "https://eoe8vj6kvm12tsx.m.pipedream.net"
    }
]
```

```http
  DELETE /api/endpoints/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `accessToken`      | `Header` | **Required** |


## API Reference for Endoints Results

```http
  GET /api/endpoints/${id}/Results
```
**response**
```json
   {
        "payload": "{\n  \"success\": true,\n  \"info\": \"To customize this response, check out our docs at https://pipedream.com/docs/workflows/steps/triggers/#customizing-the-http-response\"\n}\n",
        "statusCode": 200,
        "dateOfCheck": "2022-04-17T18:31:24.428+00:00"
    },
    {
        "payload": "{\n  \"success\": true,\n  \"info\": \"To customize this response, check out our docs at https://pipedream.com/docs/workflows/steps/triggers/#customizing-the-http-response\"\n}\n",
        "statusCode": 200,
        "dateOfCheck": "2022-04-17T18:31:34.467+00:00"
    },
    {
        "payload": "{\n  \"success\": true,\n  \"info\": \"To customize this response, check out our docs at https://pipedream.com/docs/workflows/steps/triggers/#customizing-the-http-response\"\n}\n",
        "statusCode": 200,
        "dateOfCheck": "2022-04-17T18:31:44.465+00:00"
    }
```

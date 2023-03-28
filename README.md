# web-workloads-comm
One workload client-app is calling a second workload server-app


## Running

1. Install first the `server-app`
```shell
tanzu apps workload create server-app \
--git-repo  https://github.com/jsanin-vmw/web-workloads-comm \
--sub-path  server-app \
--git-branch main \
--request-cpu 500m \
--request-memory 250Mi \
--limit-cpu 1000m \
--limit-memory 1500Mi \
--type web \
--yes \
--namespace default

```
2. Get the resulting url for the workload

You can run:
```
tanzu apps workload get app-server -n YOUR-NAMESPACE
```

And look for the section `Knative services`
```
🚢 Knative Services
   NAME         READY   URL
   server-app   Ready   https://server-app.default.YOUR-CLUSTER-DOMAIN
```
3. Install the `client-app` and set the env variable `SERVER_APP_URL` to the previous url.

```shell
tanzu apps workload create client-app \
--git-repo  https://github.com/jsanin-vmw/web-workloads-comm \
--sub-path  client-app \
--git-branch main \
--env "SERVER_APP_URL=https://server-app.default.YOUR-CLUSTER-DOMAIN" \
--request-cpu 500m \
--request-memory 250Mi \
--limit-cpu 1000m \
--limit-memory 1500Mi \
--type web \
--yes \
--namespace default
```

## Testing

1. Check the `server-app` is working:
```
curl -k https://server-app.default.YOUR-CLUSTER-DOMAIN
```
Expected output:
```
Greetings from server-app + tanzu!
```

2. Check the `client-app` is working:
```
curl -k https://client-app.default.YOUR-CLUSTER-DOMAIN
```
Expected output:
```
Greetings from client-app + Tanzu!
```

3. Check the `client-app` is calling the `server-app`
```
curl -k https://client-app.default.YOUR-CLUSTER-DOMAIN/calling-server-app
```
Expected output:
```
The server says: Greetings from server-app + tanzu!
```
 

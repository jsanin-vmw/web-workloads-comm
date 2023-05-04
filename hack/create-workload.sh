tanzu apps workload create client-app-type-web \
--git-repo  https://github.com/jsanin-vmw/web-workloads-comm \
--sub-path  client-app \
--git-branch client-recurring-server-call \
--env "SERVER_APP_URL=https://server-app-type-web-tanzusc-apps.jsanin-tanzusc.tanzu.biz" \
--request-cpu 500m \
--request-memory 250Mi \
--limit-cpu 1000m \
--limit-memory 1500Mi \
--annotation "autoscaling.knative.dev/min-scale=1" \
--type web \
--yes \
--namespace jsanin-ns


tanzu apps workload create server-app-type-web \
--git-repo  https://github.com/jsanin-vmw/web-workloads-comm \
--sub-path  server-app \
--git-branch client-recurring-server-call \
--request-cpu 500m \
--request-memory 250Mi \
--limit-cpu 1000m \
--limit-memory 1500Mi \
--type web \
--yes \
--namespace jsanin-ns

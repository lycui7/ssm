class aJax {
    constructor(xhr) {
        xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
        this.xhr = xhr;
    }
    send(options) {
        let xhr = this.xhr;
        let opt = {
            type : options.type || 'GET',
            url : options.url || '',
            async : options.async || 'true',
            dataType : options.dataType || 'json',
            queryString : options.queryString || ''
        };
        return new Promise(((resolve, reject) => {
            xhr.open(opt.type,opt.url,opt.async);
          /*  readyState ：表示请求响应过程的当前活动阶段
            0：未初始化，未调用open方法
            1： 已经调用open建立连接，未调用send
            2: 调用send,未接收到响应数据
            3： 接收中，已经接收部分响应数据
            4： 完成*/
            /*readyState属性的值由一个值变成另一个值，都会触发一次readystatechange事件*/
            xhr.onreadystatechange = () => {
                if(xhr.readyState == 4) {
                    if(xhr.status == 200) {
                        if(opt.dataType === 'json') {
                            const data = JSON.parse(xhr.responseText);
                            resolve(data);
                            console.log(data);
                        }
                    }else {
                        reject(new Error(xhr.status || 'Server is fail'));
                    }
                }
            };
            xhr.onerror = () => {
                reject(new Error(xhr.status || 'Server is fail'));
            };
            xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xhr.send(opt.queryString);
        }));
    }
}
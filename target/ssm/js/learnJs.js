$(function () {
    /*alert(context);
    alert(fullUrl);*/
    /*1.值和引用变量的赋值*/
    console.log("1.值和引用变量的赋值");
    let str = "String1";
    console.log(str);
    let person = {name : "albert.cui"};
    let person2 = person;
    person2.name = "choosl.liu";
    console.log(person);
    /*2.闭包*/
    console.log("2.闭包");
    const sayHello = createGreeter("HI");
    sayHello("albert.cui");
    /*3.解构*/
    console.log("3.解构");
    var cat = {
      name : "TOM",
      age : 20
    };
    var {name,age} = cat;
    console.log(name,age);
    //提取不同的属性名
    var {name : myName,age : myAge} = cat;
    console.log(myName,myAge);
    //调用函数
    introduce(cat);
    /*4.展开语法*/
    console.log("4.展开语法");
    const arr = [23,24,25,26,27,28];
    let result = Math.max(...arr);
    console.log(result);
    /*5.剩余语法*/
    console.log("5.剩余语法");
    myFunc(4,3,9);
    myFunc2(3,2,1,4);
    /*6.操作数组的实用方法*/
    console.log("6.操作数组的实用方法");
    console.log("①map");
    const mapped = arr.map(function (e1) {
        return e1+10;
    });
    console.log(mapped);
    console.log("②filter");
    const filter = arr.filter(e1 => e1>=25 && e1<= 27);
    console.log(filter);
    console.log("③reduce");
    const reduce = arr.reduce((total,current) => total+current);
    console.log(reduce);
    console.log("④find");
    console.log(arr.find(e1 => e1>23));
    console.log("⑤findIndex");
    console.log(arr.findIndex(e1 => e1>23));
    console.log("⑥indexOf");
    console.log(arr.indexOf(24));
    console.log("⑦push pop shift unshift")
    console.log(arr.push(100));
    console.log(arr);
    console.log(arr.pop());
    console.log(arr);
    console.log(arr.shift());
    console.log(arr);
    console.log(arr.unshift(66,77,88));
    console.log(arr);
    console.log("⑧splice(拼接) slice(部分)");
    //splice :从下标为1开始，向后移除1个元素，添加666，777 两个元素
    console.log(arr.splice(1,1,666,777));
    console.log(arr);
    console.log(arr.slice(1,4));
    console.log("⑨sort");
    console.log(arr.sort((e1,e2) => e2 - e1));
    /*7.Generators函数*/
    console.log("7.Generators函数");
    const myEnum = ENUM();
    while (myValue1 = myEnum.next().value) {
        console.log(myValue1);
    }
    /*8.相等运算符和恒等运算符*/
    console.log("8.相等运算符和恒等运算符");
    console.log("345" == 345);
    console.log(true == "true");
    console.log("345" === 345);
    /*9.比较对象*/
    console.log("9.比较对象");
    let dog1 = {name : "xixi"};
    let dog2 = {name : "xixi"};
    console.log(dog1 == dog2);
    console.log(dog1 === dog2);
    console.log(JSON.stringify(dog1) == JSON.stringify(dog2));
    /*10.回调函数*/
    console.log("10.回调函数");
    /*myFunc3("albert.cui",function () {
        $("p").text(arguments[0]);
    });*/
    myFunc3("albert.cui",e1 => $("p").text(e1));
    /*11.Promises*/
    console.log("11.Promises");
    console.log("将异步逻辑封装在Promise中，异步操作成功后执行resolve回调函数，失败后执行reject回调函数");
    console.log("Promise运行成功则进入then回调函数，失败则进入catch回调");
    const myPromise = new Promise(function (resolve, reject) {
        setTimeout(function () {
            if(Math.random() < 0.5) {
                return resolve("albert.cui");
            }
            return reject("Chools.liu");
        },5000)
    });
    myPromise.then(
        function (data) {
            $("span").text(data);
        }
    ).catch(data => $("span").text(data)).finally(() =>console.log("执行完毕"));
    /*12.Async Await*/
    console.log("12.Async await");
    console.log("async await是基于Promise的语法糖");
    const greeter = new Promise(((resolve, reject) => setTimeout(() => resolve("我真是太有才了"),5000)));
    async function myFunc4() {
        const greeting = await greeter;
        $("strong").text(greeting);
    }
    myFunc4();
});
function createGreeter(greeting) {
    return function (name) {
        console.log( greeting+" , "+name);
    };
}
function introduce({name}) {
    console.log("Hello,My name is "+name);
}
function myFunc(...args) {
    console.log(args[0]+args[2]);
}
function myFunc2() {
    console.log(arguments.length);
    console.log(arguments[0]+arguments[1]);
}
function* ENUM() {
    yield "准备中";
    yield "作业中";
    yield "暂停中";
    yield "已完成";
}
function myFunc3(message,callback) {
    setTimeout(function () {
        callback(message);
    },5000);
}

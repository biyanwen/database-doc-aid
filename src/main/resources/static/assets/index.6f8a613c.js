import{a as O,c as M,r as B,b as L,d as v,o as w,e as k,f as c,g as n,w as f,u as i,F as R,p as C,h as S,i as U,E as N,C as h,j as q,k as j,l as T}from"./vendor.ecd51fca.js";const z=function(){const r=document.createElement("link").relList;if(r&&r.supports&&r.supports("modulepreload"))return;for(const t of document.querySelectorAll('link[rel="modulepreload"]'))m(t);new MutationObserver(t=>{for(const e of t)if(e.type==="childList")for(const p of e.addedNodes)p.tagName==="LINK"&&p.rel==="modulepreload"&&m(p)}).observe(document,{childList:!0,subtree:!0});function d(t){const e={};return t.integrity&&(e.integrity=t.integrity),t.referrerpolicy&&(e.referrerPolicy=t.referrerpolicy),t.crossorigin==="use-credentials"?e.credentials="include":t.crossorigin==="anonymous"?e.credentials="omit":e.credentials="same-origin",e}function m(t){if(t.ep)return;t.ep=!0;const e=d(t);fetch(t.href,e)}};z();var J="/assets/logo.03d6d6da.png";const F=O.create({baseURL:"http://127.0.0.1:3000",timeout:6e3});function K(o){return F({url:"/api/testConnect",method:"post",data:o})}const y=M({state(){return{ip:"",port:"",userName:"",password:"",databaseName:""}},getters:{getDatabaseMessage:()=>globalThis.state},mutations:{set(o,r){o.ip=r.ip,o.port=r.port,o.userName=r.userName,o.password=r.password,o.databaseName=r.databaseName}}});var P=(o,r)=>{const d=o.__vccOpts||o;for(const[m,t]of r)d[m]=t;return d};const G=o=>(C("data-v-6616722e"),o=o(),S(),o),H=G(()=>c("div",{style:{margin:"20%"}},null,-1)),Q={class:"doc-form"},W={class:"doc-form-div"},X={class:"doc-button"},Y=U("\u8FDE\u63A5\u6D4B\u8BD5"),Z={setup(o){class r{constructor(){this._ip="",this._port="",this._userName="",this._password="",this._databaseName=""}get ip(){return this._ip}set ip(s){this._ip=s}get port(){return this._port}set port(s){this._port=s}get userName(){return this._userName}set userName(s){this._userName=s}get password(){return this._password}set password(s){this._password=s}get databaseName(){return this._databaseName}set databaseName(s){this._databaseName=s}}const d=B(),m=B("right"),t=L({ip:[{required:!0,message:"ip \u4E0D\u80FD\u4E3A\u7A7A",trigger:"blur"}],port:[{required:!0,message:"\u7AEF\u53E3 \u4E0D\u80FD\u4E3A\u7A7A",trigger:"change"}],userName:[{required:!0,message:"\u7528\u6237\u540D \u4E0D\u80FD\u4E3A\u7A7A",trigger:"change"}],password:[{required:!0,message:"\u5BC6\u7801 \u4E0D\u80FD\u4E3A\u7A7A",trigger:"change"}],databaseName:[{required:!0,message:"\u6570\u636E\u5E93\u540D\u5B57 \u4E0D\u80FD\u4E3A\u7A7A",trigger:"change"}]}),e=L(new r),p=async a=>{!a||await a.validate((s,_)=>{s&&(l(),b())})},l=()=>{let a=new r;a=Object.assign(a,e),e.password!==""&&(a.password=$(e.password)),y.commit("set",a)},b=()=>{K(y.state).then(a=>{a.status===200&&a.data===!0?E():x()}).catch(a=>{x()})},x=()=>{N({title:"Error",message:"\u64CD\u4F5C\u5931\u8D25\uFF0C\u8BF7\u68C0\u67E5\u4FE1\u606F\u662F\u5426\u586B\u5199\u6B63\u786E",type:"error"})},E=()=>{N({title:"Success",message:"\u6210\u529F\u8FDE\u63A5\u5230\u6570\u636E\u5E93",type:"success"})},$=a=>{let s=h.enc.Utf8.parse("database-doc-aid"),_=h.enc.Utf8.parse(a);return h.AES.encrypt(_,s,{mode:h.mode.ECB,padding:h.pad.Pkcs7}).toString()};return(a,s)=>{const _=v("el-input"),g=v("el-form-item"),A=v("el-form"),I=v("el-button");return w(),k(R,null,[H,c("div",Q,[c("div",W,[n(A,{"label-position":m.value,"label-width":"100px",model:i(e),style:{"max-width":"80%"},rules:i(t),ref_key:"ruleFormRef",ref:d},{default:f(()=>[n(g,{label:"IP",prop:"ip"},{default:f(()=>[n(_,{onBlur:l,modelValue:i(e).ip,"onUpdate:modelValue":s[0]||(s[0]=u=>i(e).ip=u)},null,8,["modelValue"])]),_:1}),n(g,{label:"\u7AEF\u53E3",prop:"port"},{default:f(()=>[n(_,{onBlur:l,modelValue:i(e).port,"onUpdate:modelValue":s[1]||(s[1]=u=>i(e).port=u)},null,8,["modelValue"])]),_:1}),n(g,{label:"\u7528\u6237\u540D",prop:"userName"},{default:f(()=>[n(_,{onBlur:l,modelValue:i(e).userName,"onUpdate:modelValue":s[2]||(s[2]=u=>i(e).userName=u)},null,8,["modelValue"])]),_:1}),n(g,{label:"\u5BC6\u7801",prop:"password"},{default:f(()=>[n(_,{onBlur:l,type:"password",modelValue:i(e).password,"onUpdate:modelValue":s[3]||(s[3]=u=>i(e).password=u)},null,8,["modelValue"])]),_:1}),n(g,{label:"\u6570\u636E\u5E93",prop:"databaseName"},{default:f(()=>[n(_,{onBlur:l,modelValue:i(e).databaseName,"onUpdate:modelValue":s[4]||(s[4]=u=>i(e).databaseName=u)},null,8,["modelValue"])]),_:1})]),_:1},8,["label-position","model","rules"])])]),c("div",X,[n(I,{type:"primary",onClick:s[5]||(s[5]=u=>p(d.value))},{default:f(()=>[Y]),_:1})])],64)}}};var D=P(Z,[["__scopeId","data-v-6616722e"]]);function ee(o){return F({url:"/api/createPdf",method:"post",data:o,responseType:"blob"})}const te=o=>(C("data-v-2cef3476"),o=o(),S(),o),se={class:"box"},oe={class:"leftBox"},re={class:"rightBox"},ae=te(()=>c("div",{class:"aid-img-div"},[c("img",{alt:"Vue logo",src:J})],-1)),ne={class:"aid-button"},le=U("\u751F\u6210PDF\u6587\u6863"),ie={setup(o){const r=()=>{ee(y.state).then(t=>{if(t.status===200){const e=t.headers["content-disposition"].split("=utf-8")[1];m(t.data,e)}else d()}).catch(()=>{d()})},d=()=>{N({title:"Error",message:"\u64CD\u4F5C\u5931\u8D25\uFF0C\u8BF7\u68C0\u67E5\u4FE1\u606F\u662F\u5426\u586B\u5199\u6B63\u786E",type:"error"})},m=(t,e)=>{let p=new Blob([t],{type:"application/pdf"}),l=document.createElement("a"),b=window.URL.createObjectURL(p);l.href=b,l.download=e,document.body.appendChild(l),l.click(),document.body.removeChild(l),window.URL.revokeObjectURL(b)};return(t,e)=>{const p=v("el-button");return w(),k("div",se,[c("div",oe,[n(D)]),c("div",re,[ae,c("div",ne,[n(p,{onClick:r,type:"primary",size:"large"},{default:f(()=>[le]),_:1})])])])}}};var de=P(ie,[["__scopeId","data-v-2cef3476"]]);const ue={setup(o){return(r,d)=>(w(),q(de))}};const V=j(ue);V.use(T);V.use(y);V.mount("#app");

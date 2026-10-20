(function(root){
'use strict';
const fail=(message,status=400)=>{const e=new Error(message);e.status=status;throw e;};
function validate(m,data){
 const result={};
 if(!data||Object.keys(data).some(k=>!m.fields.some(f=>f.key===k)))fail('O cadastro contém campos desconhecidos.');
 for(const f of m.fields){let v=data[f.key];if(typeof v==='string')v=v.trim();
  if(v===undefined||v===null||v===''){if(f.required)fail(`${f.label} é obrigatório.`);result[f.key]=f.kind==='number'?null:'';continue;}
  if(f.kind==='number'){if(typeof v!=='number'||!Number.isInteger(v)||v<f.min||v>f.max)fail(`${f.label} deve ser um inteiro de ${f.min} a ${f.max}.`);}
  else if(f.kind==='list'){if(!Array.isArray(v)||!v.length||v.some(x=>typeof x!=='string'||!x.trim()||x.trim().length>f.max))fail(`${f.label}: inclua pelo menos um componente válido, com até ${f.max} caracteres.`);v=v.map(x=>x.trim());}
  else {if(typeof v!=='string')fail(`${f.label} deve ser texto.`);if(v.length<f.min||(f.max>0&&v.length>f.max))fail(`${f.label} deve ter ${f.min} a ${f.max} caracteres.`);
   if(f.kind==='select'&&!f.options.includes(v))fail(`${f.label} possui uma opção inválida.`);
   if(f.kind==='singleline'&&/[\r\n]/.test(v))fail(`${f.label} deve ocupar uma única linha.`);
   if(f.kind==='email'&&!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(v))fail(`${f.label} deve ter formato de e-mail.`);
   if(f.kind==='url'){try{let u=new URL(v);if(!['http:','https:'].includes(u.protocol)||!u.hostname)throw Error();}catch{fail(`${f.label} deve ser uma URL HTTP ou HTTPS com host.`);}}
  }result[f.key]=v;
 }return result;
}
function initial(m){return m.policy==='conteudo'?'OCULTO':'ATIVO';}
function canEdit(m,r){return m.policy==='operacao'||r.estado!=='ARQUIVADO';}
function transition(m,r,action){
 if(action==='arquivar'&&r.estado!=='ARQUIVADO')return 'ARQUIVADO';
 if(action==='restaurar'&&r.estado==='ARQUIVADO')return initial(m);
 if(m.policy==='conteudo'&&action==='publicar'&&r.estado==='OCULTO')return 'VISIVEL';
 if(m.policy==='conteudo'&&action==='ocultar'&&r.estado==='VISIVEL')return 'OCULTO';
 fail('A ação não é permitida no estado atual.',409);
}
const Core={validate,initial,canEdit,transition,fail};
if(typeof module!=='undefined'&&module.exports)module.exports=Core;else root.CampusCore=Core;
})(typeof window!=='undefined'?window:globalThis);

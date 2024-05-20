/**
 * while.html에 포함.
 */

 const list = document.querySelector('#list');
 // document.getElementById('list');
 
 const tableBody = document.querySelector('#tableBody');
 
 let html = '';
 let table = '';
 let n = 1;
 
 while (n <= 5) {
    html += `<li>아이템 ${n} </li>`;
    table += `
        <tr>
            <td>${n}</td>
            <td>제목 ${n}</td>
        </tr>
    `;
    n++;
 }
 list.innerHTML = html;
 tableBody.innerHTML = table;
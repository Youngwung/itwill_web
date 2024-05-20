/**
 * switch.html에 포함.
 */

// console.log('test');

const weekday = document.getElementById('weekday');
const btn = document.getElementById('btn');
const result = document.getElementById('result');

function selectLinstener() {
    const value = weekday.value;
    switch (value) {
        case 'mon':
        case 'tue':
        case 'wed':
        case 'thu':
        case 'fri':
            result.innerHTML = '학원 출석.'
            break;
        case 'sat':
        case 'sun':
            result.innerHTML = `늦잠자기.`
            break;
        default:
            result.innerHTML = `4차원?`
    }
}

btn.addEventListener('click', selectLinstener);
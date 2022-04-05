let answerButtons = [{id: 'a', isSelected: false}, {id: 'b', isSelected: false}, {id: 'c', isSelected: false}, {
    id: 'd',
    isSelected: false
}];

let ipAddress = '192.168.179.11';

function register() {
    let name = document.getElementById("nameInput").value;

    if (name == "")
        alert("Name kann nicht leer sein, Idiot!")
    else if (name == null)
        alert("Bitte Namen eingeben")
    else {
        localStorage.setItem("username", name);


        fetch('http://'+ipAddress+':8080/QuizzicalApp-1.0/api/player/' + name, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        })
            .then(response => response.json())
            .then(data => {
                window.location = "main.html";
            })
            .catch(function (err) {
                console.log(err);
            });

    }


}

function initMainPage() {
    document.getElementById("groupname").innerHTML = "Team " + localStorage.getItem("username");

}

function selectAnswer(id) {
    for (let i = 0; i < answerButtons.length; i++) {
        if (answerButtons[i].id === id && !answerButtons[i].isSelected) {
            document.getElementById(id).style.backgroundColor = "#74d659";
            answerButtons[i].isSelected = true;
        } else {
            answerButtons[i].isSelected = false;
            document.getElementById(answerButtons[i].id).style.backgroundColor = "#bebebe";
            document.getElementById(answerButtons[i].id).style.border = "#bebebe";


        }

    }


}

function submitAnswer() {
    let buttonAnswer = false;
    let submittedAnswer;
    let textAnswer = document.getElementById('textAnswer').value;
    let username = localStorage.getItem("username");


    for (let i = 0; i < answerButtons.length; i++) {
        if (answerButtons[i].isSelected) {
            buttonAnswer = true;
            submittedAnswer = answerButtons[i].id;
            break;
        }

    }

    if (textAnswer !== '' && buttonAnswer) {
        alert("Du bist ein Idiot. Entweder Text ODER Multiple Choice, NICHT BEIDES!")
    } else {
        if (!buttonAnswer) {
            submittedAnswer = textAnswer;
        }

        if (username === '' || username === null) {
            alert("Gruppenname nicht eingetragen, gehe erst auf die Startseite!");
        } else {

            fetch('http://'+ipAddress+':8080/QuizzicalApp-1.0/api/player/' + username, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: submittedAnswer
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    alert('Antwort abgeschickt!');
                    selectAnswer('null');
                    document.getElementById('textAnswer').value = "";



                })
                .catch(function (err) {
                    console.log(err);
                });
        }
    }
}

function loadLeaderBoard(){
    fetch('http://'+ipAddress+':8080/QuizzicalApp-1.0/api/player/', {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            let row,place,name,points,answer;
           let table = document.getElementById("leaderTable")
            for (let i = 1; i < data.length+1; i++) {
                row = table.insertRow(i);
                place = row.insertCell(0);
                name = row.insertCell(1);
                points = row.insertCell(2);
                answer = row.insertCell(3);
                place.innerHTML = data[i-1].place+".";
                name.innerHTML = data[i-1].name;
                points.innerHTML = data[i-1].totalPoints;
                answer.innerHTML = data[i-1].currentAnswer;

            }

        })
        .catch(function (err) {
            console.log(err);
        });
}
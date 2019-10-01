<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Question</title>
    <style>
        button {
            width: 10%;
            background-color: aquamarine;
        }
        
        #options {
            border: 1px solid black;
        }
        
        .option {
            background-color: cornsilk;
        }
    </style>
    <script>
      let optionsQuantity = 1;
      
      function toMainMenu() {
        window.location.href = "navigation";
      }
      
      function saveQuestion() {
        console.log("saveQuestion");
        //at least one option must be correct
        //at least one option must be not correct
      }
      
      function getNewOption() {
        
        const newDiv = document.createElement("div");
        newDiv.className = "option";
        const labelForOption = document.createElement("label");
        labelForOption.innerText = "Вопрос:";
        const optionTextArea = document.createElement("textarea");
        const currentOptionsQuantity = optionsQuantity;
        optionTextArea.setAttribute("id", "option-" + currentOptionsQuantity);
        optionTextArea.setAttribute("maxlength", "255");
        labelForOption.setAttribute("for", "option-" + currentOptionsQuantity);
        const labelForOptionCorrect = document.createElement("label");
        labelForOptionCorrect.innerText = "Правильный ответ?";
        const optionCheckbox = document.createElement("input");
        optionCheckbox.setAttribute("id", "optionCorrect-" + currentOptionsQuantity);
        optionCheckbox.setAttribute("type", "checkbox");
        labelForOptionCorrect.setAttribute("for", "optionCorrect-" + currentOptionsQuantity);
        
        // <label for="option-1">Вопрос:</label>
        //   <textarea id="option-1" maxlength="255"></textarea>
        //     <label for="optionCorrect-1">Правильный ответ?</label>
        //   <input id="optionCorrect-1" type="checkbox"/>
        newDiv.appendChild(labelForOption);
        newDiv.appendChild(optionTextArea);
        newDiv.appendChild(labelForOptionCorrect);
        newDiv.appendChild(optionCheckbox);
        return newDiv;
      }
      
      function addOption() {
        optionsQuantity++;
        const optionsSection = document.getElementById("options");
        optionsSection.appendChild(getNewOption())
      }
    </script>
</head>
<body>
<h1>Добавить вопрос</h1>
<br/>
<div>
    <button onclick="toMainMenu()">To main menu</button>
    <button onclick="saveQuestion()">Сохранить вопрос</button>
</div>
<br/>
<div>
    <div>
        <label for="testName">Название теста:</label>
        <textarea id="testName" disabled maxlength="255"></textarea>
    </div>
    <div>
        <label for="question">Вопрос:</label>
        <textarea id="question" maxlength="255"></textarea>
    </div>
    <div>
        <button onclick="addOption()">Добавить вариант ответа</button>
    </div>
</div>
<div id="options">
    <div class="option">
        <label for="option-1">Вопрос:</label><textarea id="option-1" maxlength="255"></textarea><label
            for="optionCorrect-1">Правильный ответ?</label><input id="optionCorrect-1" type="checkbox"/>
    </div>
</div>

</body>
</html>

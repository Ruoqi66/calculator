const result = document.getElementById("result");
const expression = document.getElementById("expression");
let current = "0";
let previous = null;
let operator = null;
let reset = false;

function render() {
    result.textContent = current;
}

function calculate() {
    const a = Number(previous);
    const b = Number(current);
    let value;
    if (operator === "+") value = a + b; else if (operator === "-") value = a - b; else if (operator === "*") value = a * b; else if (operator === "/") {
        if (b === 0) {
            current = "不能除以零";
            previous = null;
            operator = null;
            reset = true;
            expression.textContent = "";
            render();
            return;
        }
        value = a / b;
    }
    current = String(Number(value.toPrecision(12)));
    expression.textContent = `${a} ${operator} ${b} =`;
    previous = null;
    operator = null;
    reset = true;
    render();
}

document.querySelector(".keys").addEventListener("click", event => {
    const button = event.target.closest("button");
    if (!button) return;

    if (button.dataset.num !== undefined) {
        const digit = button.dataset.num;
        if (reset || current === "不能除以零") {
            current = digit === "." ? "0." : digit;
            reset = false;
        } else if (digit !== "." || !current.includes(".")) {
            current = current === "0" && digit !== "." ? digit : current + digit;
        }
        render();
        return;
    }

    if (button.dataset.op) {
        if (operator && previous !== null && !reset) calculate();
        previous = current;
        operator = button.dataset.op;
        expression.textContent = `${previous} ${operator}`;
        reset = true;
        return;
    }

    const action = button.dataset.action;
    if (action === "clear") {
        current = "0";
        previous = null;
        operator = null;
        reset = false;
        expression.textContent = "";
    } else if (action === "sign" && current !== "不能除以零") {
        current = String(-Number(current));
    } else if (action === "percent" && current !== "不能除以零") {
        current = String(Number(current) / 100);
    } else if (action === "backspace") {
        if (reset || current === "不能除以零") {
            current = "0";
            reset = false;
        } else {
            current = current.length > 1 ? current.slice(0, -1) : "0";
        }
    } else if (action === "equals" && operator !== null && previous !== null) {
        calculate();
    }
    render();
});
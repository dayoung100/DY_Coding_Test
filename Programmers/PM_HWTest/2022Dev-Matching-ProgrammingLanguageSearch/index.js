// index.js : 애플리케이션의 시작점 역할
import createComponent from "./core/component.js";
import App from "./App.js";

const render = () => {
  const $app = document.getElementById("app");
  const appComponent = createComponent(App);
  $app.innerHTML = appComponent.element;
};

render();

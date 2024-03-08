export default function Suggestion({ suggestion }) {
  return {
    element: `
        <div class="Suggestion">
        <ul>
            ${suggestion.map(item => `<li>${item}</li>`).join("")}
            <li class="Suggestion__item--selected">Action<span class="Suggestion__item--matched">Script</span></li>
            <li>Java<span class="Suggestion__item--matched">Script</span></li>
            <li>Type<span class="Suggestion__item--matched">Script</span></li>
            <li>Pure<span class="Suggestion__item--matched">Script</span></li>
        </ul>
      </div> 
        `,
  };
}

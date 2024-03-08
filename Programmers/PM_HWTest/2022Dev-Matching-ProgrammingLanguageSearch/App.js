//App.js : 컴포넌트의 구성과 렌더링 역할
import createComponent from "./core/component.js";
import SearchInput from "./components/SearchInput.js";
import SelectedLanguage from "./components/SelectedLanguage.js";
import Suggestion from "./components/Suggestion.js";

function App() {
  const state = {
    inputValue: "",
    suggestion: ["JavaScript", "TypeScript", "PureSrcipt"],
    selected: ["JavaScript", "Python", "Elixir", "Java", "PHP"],
  };

  const setState = {};

  const selectedLanguageComponent = createComponent(SelectedLanguage, { selected: state.selected });
  const searchInputComponent = createComponent(SearchInput, { inputValue: state.inputValue });
  const suggestionComponent = createComponent(Suggestion, { suggestion: state.suggestion });

  return {
    element: `
            ${selectedLanguageComponent.element}
            ${searchInputComponent.element}
            ${suggestionComponent.element}
        `,
  };
}

export default App;

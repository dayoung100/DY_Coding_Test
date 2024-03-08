function SelectedLanguage({ selected }) {
  return {
    element: `
        <div class="SelectedLanguage">
        <ul>
            ${selected.map(item => `<li>${item}</li>`).join("")}
        </ul>
      </div> 
        `,
  };
}
export default SelectedLanguage;

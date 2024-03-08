function SearchInput({ inputValue }) {
  return {
    element: `
        <form class="SearchInput">
            <input class="SearchInput__input" type="text" placeholder="프로그램 언어를 입력하세요." value=${inputValue}>
        </form>
        `,
  };
}
export default SearchInput;

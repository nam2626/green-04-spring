export default ({ pagging, onPageChange } ) => {

  return <ul>
    <li>
      <button disabled={pagging.priviousPageGroup} onClick={() => onPageChange(pagging.startPageOfPageGroup - 1)}>◀◀</button>
    </li>
    <li>
      <button disabled={pagging.nextPageGroup} onClick={() => onPageChange(pagging.endPageOfPageGroup + 1)}>▶▶</button>
    </li>

  </ul>
}
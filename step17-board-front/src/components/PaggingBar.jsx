export default ({ pagging, onPageChange } ) => {

<<<<<<< HEAD
  const pageNumbers = [];
  for(i = pagging.startPageOfPageGroup ; i <= pagging.endPageOfPageGroup; i++)
    pageNumbers.push(i);

=======
>>>>>>> 34ae5e09a287774f8596861b485a8b393459cc74
  return <ul>
    <li>
      <button disabled={pagging.priviousPageGroup} onClick={() => onPageChange(pagging.startPageOfPageGroup - 1)}>◀◀</button>
    </li>
<<<<<<< HEAD
    {
      pageNumbers.map(item => <li key={item}>
        <button onClick={() => onPageChange(item)} disabled={item === pagging.currentPage}>{item}</button>
      </li>)
    }
=======
>>>>>>> 34ae5e09a287774f8596861b485a8b393459cc74
    <li>
      <button disabled={pagging.nextPageGroup} onClick={() => onPageChange(pagging.endPageOfPageGroup + 1)}>▶▶</button>
    </li>

  </ul>
}
import React, { useEffect, useState } from "react";
import axios from "axios";

const Pagination = ({ setList }) => {
  // 인증 관련 코드 작성

  const [total, setTotal] = useState("");
  const pageNumbers = [];
  for (let i = 1; i <= Math.ceil(parseInt(total) / 10); i++) {
    pageNumbers.push(i);
  }

  const getNextNotices = (number) => {
    axios
      .get(
        `https://jsonplaceholder.typicode.com/posts?page=${number}`
      )
      .then((res) => setList(res.data.results))
      .catch((err) => console.log(err));
  };

  const getNotice = async () => {
    try {
      const response = await axios.get(
        `https://jsonplaceholder.typicode.com/posts`
      );
      console.log(response.json());
      setList(response.json());
      setTotal(response.count);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getNotice();
  }, []);
  return (
    <>
      <div>
        {pageNumbers.map((number) => (
          <div
            key={number}
            onClick={() => getNextNotices(number)}
          >
            <div>{number}</div>
          </div>
        ))}
      </div>
    </>
  );
};

export default Pagination;

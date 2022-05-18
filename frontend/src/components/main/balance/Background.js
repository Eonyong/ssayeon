import styles from "./css/Backround.module.css";

import background from "../../../../public/background.jpeg";

const Background = () => {
    return (
        <article
            className={styles.article}
            style={{ backgroundImage: `url(${background})` }}
        >
            <h1 className={styles.header}>React Is Awesome</h1>
        </article>
    );
};

export default Background;

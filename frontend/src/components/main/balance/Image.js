import styles from "./css/Image.moudle.css";

import background from "./css/background.jpeg";


const Image = () => {
    return (
        <article className={styles.article}>
            <img className={styles.picture} src={background} alt="background" />
            <h1 className={styles.header}>React Is Awesome</h1>
        </article>
    );
};

export default Image;

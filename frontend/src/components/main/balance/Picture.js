import styles from "./css/Picture.module.css";

import background from "./css/background.jpeg";


const Picture = ({text}) => {
    return (
        <article className={styles.article}>
            <picture className={styles.image}>
                <source media="(min-width: 0px)" srcSet={background} />
                <img src={background} alt="background" />
            </picture>
            <h1 className={styles.text}>{text}</h1>
        </article>
    );
};

export default Picture;

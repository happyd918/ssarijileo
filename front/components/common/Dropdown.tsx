import { useState, useEffect } from 'react';
import classNames from 'classnames';

import styles from '@/styles/common/Dropdown.module.scss';

function Dropdown(props: any) {
  const { children, visibility } = props;
  const [animation, setAnimation] = useState(false);
  const [repeat, setRepeat] = useState(0);

  useEffect(() => {
    if (visibility) {
      clearTimeout(repeat);
      setRepeat(0);
      setAnimation(true);
    } else {
      const timeoutId = window.setTimeout(() => setAnimation(false), 400);
      setRepeat(timeoutId);
    }
  }, [visibility]);

  return (
    <article
      className={classNames(
        visibility ? styles.fadeIn : styles.fadeOut,
        styles.dropdown,
      )}
    >
      {animation && children}
    </article>
  );
}

export default Dropdown;

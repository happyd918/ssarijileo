import { Html, Head, Main, NextScript } from 'next/document';

export default function Document() {
  return (
    <Html lang="en">
      <Head>
        {/* 카카오 API 사용을 위한 SDK 스크립트 추가 */}
        {/* <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script> */}
        <script
          src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
          // integrity=process.env.NEXT_APP_KAKAO_SDK_INTEGRITY
          crossorigin="anonymous"
        ></script>

        <link
          href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&family=Miniver&display=swap"
          rel="stylesheet"
        />
        <link
          href="https://fonts.googleapis.com/css2?family=Miniver&display=swap"
          rel="stylesheet"
        />
      </Head>
      <body>
        <Main />
        <NextScript />
      </body>
    </Html>
  );
}

import { NextPage } from 'next';
import { useRouter } from 'next/router';
import { useCallback, useEffect } from 'react';

interface ResponseType {
  ok: boolean;
  error?: any;
}

function Kakao() {
  const router = useRouter();
  const { code: authCode, error: kakaoServerError } = router.query;

  const loginHandler = useCallback(
    async (code: string | string[]) => {
      const response: ResponseType = await fetch(
        'http://192.168.31.64:8090/oauth2/authorization/kakao',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            //   인가 코드
            authCode: code,
          }),
        },
      ).then(res => res.json());
      if (response.ok) {
        router.push('/');
      } else {
        console.log('error');
      }
    },
    [router],
  );

  useEffect(() => {
    if (authCode) {
      loginHandler(authCode);
    } else if (kakaoServerError) {
      console.log('error');
    }
  }, [loginHandler, authCode, kakaoServerError, router]);

  return <h2>로그인 중입니다..</h2>;
}

export default Kakao;
